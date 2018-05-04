package br.com.project.crud_mongo.logs;

import br.com.project.crud_mongo.models.Person;
import br.com.project.crud_mongo.services.PersonService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PersonBatch {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    @Scheduled(cron = "0,30 * * * * *")
    public void cronJob() {
        logger.info("***** > ScheduleJob *****");

        Collection<Person> people = personService.getPeople();
        logger.info("There are {} People in the data base.",
                people.size());

        logger.info("***** < ScheduleJob *****");
    }
}
