//package com.dramakill.batch.dbread;
//
//
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchTestConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//
//    private Tasklet geTasklet(){
//        Tasklet tasklet = (StepContribution contribution,ChunkContext chunkContext)->{
//            System.out.println("步骤名 ：" + chunkContext.getStepContext().getStepName() + "——————"
//                    + "线程名 ：" + Thread.currentThread().getName());
//            return RepeatStatus.FINISHED;
//        };
//        return tasklet;
//    }
//
//
//    @Bean
//    public Step stepBefore(){
//        System.out.println("[This is stepBefore]");
//        Step step = stepBuilderFactory.get("stepBefore").tasklet(geTasklet()).build();
//        return step;
//    }
//
//    @Bean
//    public Step step2(){
//        System.out.println("[This is step2]");
//        Step step = stepBuilderFactory.get("step2").tasklet(geTasklet()).build();
//        return step;
//    }
//
//    @Bean
//    public Step step3(){
//        System.out.println("[This is step3]");
//        Step step = stepBuilderFactory.get("step3").tasklet(geTasklet()).build();
//        return step;
//    }
//    @Bean
//    public Flow flow1(){
//        return new FlowBuilder<Flow>("flow1")
//                .start(stepBefore())     //可以start开始一个step、flow、决策者
//                .build();
//    }
//
//    @Bean
//    public Flow flow2(){
//        return new FlowBuilder<Flow>("flow2")
//                .start(step2())     //可以start开始一个step、flow、决策者
//                .build();
//    }
//
//
//    @Bean
//    public Job job1(){
//        Job job = jobBuilderFactory.get("job1")
//                .start(step3())  //如果start的是一个step，next不能是flow，只能是step或者决策者；但可以用On
//                .on(ExitStatus.COMPLETED.getExitCode()).to(flow1())
//                .on(ExitStatus.COMPLETED.getExitCode()).to(flow2())
//                .end()
//                .build();
//        return job;
//    }
//
//
//
//
//    @Bean
//    public ItemReader<Person> itemReader(DataSource dataSource) throws Exception {
//
//        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReader<>();
//        itemReader.setDataSource(dataSource);
//        itemReader.setSql("select * from people");
//
//        itemReader.setRowMapper(new BeanPropertyRowMapper<>(Person.class));
//
//        ExecutionContext executionContext = new ExecutionContext();
//        itemReader.open(executionContext);
//        Object customerCredit = new Object();
//        while (customerCredit != null) {
//            customerCredit = itemReader.read();
//        }
//        itemReader.close();
//        return itemReader;
//    }
//
//    @Bean
//    public PersonItemBeforeProcessor processor() {
//        return new PersonItemBeforeProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<DealBean> beforeWriter(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<DealBean>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("UPDATE people SET send_pub_before = (:SendPubBefore) WHERE person_id = (:person_id)")
//                .dataSource(dataSource)
//                .build();
//    }
//
//}