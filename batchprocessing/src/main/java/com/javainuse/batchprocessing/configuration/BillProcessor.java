package com.javainuse.batchprocessing.configuration;

import com.javainuse.batchprocessing.model.Bill;
import com.javainuse.batchprocessing.model.Usage;
import org.springframework.batch.item.ItemProcessor;

public class BillProcessor implements ItemProcessor<Usage, Bill> {

    @Override
    public Bill process(Usage usage) {
        Double billAmount = usage.getDataUsage() * .001 + usage.getMinutes() * .01;
        return new Bill(usage.getId(), usage.getFirstName(), usage.getLastName(),
                usage.getDataUsage(), usage.getMinutes(), billAmount);
    }
}
