package com.project.tondeuse.batch;

import com.project.tondeuse.entites.Tondeuse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TondeuseItemProcessor implements ItemProcessor<Tondeuse, Tondeuse> {

    @Override
    public Tondeuse process(Tondeuse tondeuse) {
        tondeuse.executeInstructions();
        return tondeuse;
    }
}
