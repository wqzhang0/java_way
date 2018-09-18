package com.dramakill.batch.dbread.before;

import com.dramakill.batch.dbread.base.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemBeforeProcessor implements ItemProcessor<Person, DealBeforeBean> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemBeforeProcessor.class);

    @Override
    public DealBeforeBean process(final Person person) throws Exception {
//        final String firstName = person.getFirst_name().toUpperCase();
//        final String lastName = person.getLast_name().toUpperCase();

        final DealBeforeBean transformedPerson = new DealBeforeBean(person.getPerson_id());
        transformedPerson.setSendPubBefore(true);
        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}