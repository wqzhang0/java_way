package com.dramakill.batch.dbread;


import com.dramakill.batch.dbread.after.DealAfterBean;
import com.dramakill.batch.dbread.after.PersonItemAfterProcessor;
import com.dramakill.batch.dbread.base.DealBean;
import com.dramakill.batch.dbread.base.Person;
import com.dramakill.batch.dbread.before.DealBeforeBean;
import com.dramakill.batch.dbread.before.PersonItemBeforeProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Person> itemReader(DataSource dataSource) throws Exception {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReader<>();
        itemReader.setDataSource(dataSource);
        itemReader.setSql("select * from people");

        itemReader.setRowMapper(new BeanPropertyRowMapper<>(Person.class));

        ExecutionContext executionContext = new ExecutionContext();
        itemReader.open(executionContext);
        Object customerCredit = new Object();
        while (customerCredit != null) {
            customerCredit = itemReader.read();
        }
        itemReader.close();
        return itemReader;
    }

    @Bean
    public PersonItemBeforeProcessor personItemBeforeProcessor() {
        return new PersonItemBeforeProcessor();
    }

    @Bean
    public PersonItemAfterProcessor personItemAfterProcessor() {
        return new PersonItemAfterProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<DealBeforeBean> beforeWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<DealBeforeBean>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("UPDATE people SET send_pub_before = (:SendPubBefore) WHERE person_id = (:person_id)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<DealAfterBean> afterWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<DealAfterBean>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("UPDATE people SET send_pub_after = (:sendPubAfter) WHERE person_id = (:person_id)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step stepBefore(ItemReader<Person> itemReader, JdbcBatchItemWriter<DealBeforeBean> writer) {
        return stepBuilderFactory.get("stepBefore").<Person, DealBeforeBean>chunk(1).reader(itemReader).processor(personItemBeforeProcessor()).writer(writer).build();
    }

    @Bean
    public Step stepAfter(ItemReader<Person> itemReader, JdbcBatchItemWriter<DealAfterBean> writer) {
        return stepBuilderFactory.get("step_after").<Person, DealAfterBean>chunk(1).reader(itemReader).processor(personItemAfterProcessor()).writer(writer).build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step stepBefore, Step stepAfter) {
        return jobBuilderFactory.get("job1")
                .start(stepBefore)
                .next(stepAfter)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
//                .flow(step1)
//                .end()
                .build();
    }
}