package Demo;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawRepository {
    private List<List<Draw>> drawRepository;

    DrawRepository() {
        drawRepository = new ArrayList<>();
    }

    public void create() {
        drawRepository.add(new ArrayList<Draw>());
    }


    public void draw(int i, int x, int y, int bold, Color color) {
        drawRepository.get(i).add(new Draw(x, y, bold, color));
    }

    public List<Draw> get(int i) {
        return drawRepository.get(i);
    }
    public Draw getDraw(int i, int j) {
        return drawRepository.get(i).get(j);
    }

    public int size() {
        return drawRepository.size();
    }

    public void delete() {
        drawRepository.clear();
    }
    public void delete(int i) {
        drawRepository.remove(i);
    }


}
