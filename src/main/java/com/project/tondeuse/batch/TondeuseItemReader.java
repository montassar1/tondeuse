package com.project.tondeuse.batch;

import com.project.tondeuse.entites.Lawn;
import com.project.tondeuse.entites.Tondeuse;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class TondeuseItemReader implements ItemReader<Tondeuse> {

    private List<Tondeuse> tondeuses;
    private int nextTondeuseIndex;

    public TondeuseItemReader() {
        tondeuses = new ArrayList<>();
        nextTondeuseIndex = 0;
        loadTondeuses();
    }

    private void loadTondeuses() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/tondeuses.txt"))) {
            String lawnDimensions = reader.readLine();
            String[] dimensions = lawnDimensions.split(" ");
            int lawnWidth = Integer.parseInt(dimensions[0]);
            int lawnHeight = Integer.parseInt(dimensions[1]);
            Lawn lawn = new Lawn(lawnWidth, lawnHeight);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] initialPosition = line.split(" ");
                int x = Integer.parseInt(initialPosition[0]);
                int y = Integer.parseInt(initialPosition[1]);
                char orientation = initialPosition[2].charAt(0);

                String instructions = reader.readLine();
                Tondeuse tondeuse = new Tondeuse(x, y, orientation, lawn, instructions);
                tondeuses.add(tondeuse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tondeuse read() {
        if (nextTondeuseIndex < tondeuses.size()) {
            return tondeuses.get(nextTondeuseIndex++);
        } else {
            return null;
        }
    }
}
