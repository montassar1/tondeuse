package com.project.tondeuse.batch;

import com.project.tondeuse.entites.Tondeuse;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class TondeuseItemWriter implements ItemWriter<Tondeuse> {

    @Override
    public void write(Chunk<? extends Tondeuse> chunk) {
        for (Tondeuse tondeuse : chunk) {
            System.out.println(tondeuse);
        }
    }
}
