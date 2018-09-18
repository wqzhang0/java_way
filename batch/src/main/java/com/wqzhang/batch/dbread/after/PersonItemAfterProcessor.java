package com.dramakill.batch.dbread.after;

import com.dramakill.batch.dbread.base.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemAfterProcessor implements ItemProcessor<Person, DealAfterBean> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemAfterProcessor.class);

    @Override
    public DealAfterBean process(final Person person) throws Exception {
//        final String firstName = person.getFirst_name().toUpperCase();
//        final String lastName = person.getLast_name().toUpperCase();

        final DealAfterBean transformedPerson = new DealAfterBean(person.getPerson_id());
        transformedPerson.setSendPubAfter(true);
        log.info("send msg to (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}