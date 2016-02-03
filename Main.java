
import java.util.ArrayList;
/**
 * A robot is located in the upper-left corner of a 4×4 grid. 
 * The robot can move either up, down, left, or right, but cannot go to the same location twice. 
 * The robot is trying to reach the lower-right corner of the grid. 
 * This program prints the number of unique ways to reach the destination.
 * @author Felipe Ronderos
 */
public class Main {
    public static void main(String[] args) {
        Integer maxX, maxY;
        //set the dimensions of the grid to 4 by 4
        maxX = maxY = 4;
        //An ArrayList used as a Queue
        ArrayList<PartialSolution> partialSolutions = new ArrayList<>();
        ArrayList<PartialSolution> finalSolutions = new ArrayList<>();
        //initial position
        Position<Integer> start = new Position<>(0,0);
        ArrayList<Position<Integer>> startVisited = new ArrayList<Position<Integer>>();
        startVisited.add(start);
        partialSolutions.add(new PartialSolution(startVisited,start));
        //final position
        Position<Integer> end = new Position<>(maxX-1,maxY-1);
        //Partial solutions are popped off the queue and replaced by possible solitions with the same path
        while (partialSolutions.size()>0){
            PartialSolution currentPath = partialSolutions.remove(0);
            Position<Integer> last = currentPath.current;
            Position<Integer> next;
            ArrayList<Position<Integer>> visited;
            if (last.getX()+1 < maxX){
                next = new Position<Integer>(last.getX()+1,last.getY());
                if (!currentPath.visited.contains(next)){
                    visited = new ArrayList<>(currentPath.visited);
                    visited.add(next);
                    if (next.equals(end))
                        finalSolutions.add(new PartialSolution(visited,next));
                    else {
                        partialSolutions.add(new PartialSolution(visited,next));
                    }
                }
            }
            if (last.getY()+1 < maxY){
                next = new Position<Integer>(last.getX(),last.getY()+1);
                if (!currentPath.visited.contains(next)){
                    visited = new ArrayList<>(currentPath.visited);
                    visited.add(next);
                    if (next.equals(end))
                        finalSolutions.add(new PartialSolution(visited,next));
                    else {
                        partialSolutions.add(new PartialSolution(visited,next));
                    }
                }
            }
            if (last.getX()-1 > -1){
                next = new Position<Integer>(last.getX()-1,last.getY());
                if (!currentPath.visited.contains(next)){
                    visited = new ArrayList<>(currentPath.visited);
                    visited.add(next);
                    if (next.equals(end))
                        finalSolutions.add(new PartialSolution(visited,next));
                    else {
                        partialSolutions.add(new PartialSolution(visited,next));
                    }
                }
            }
            if (last.getY()-1 > -1){
                next = new Position<Integer>(last.getX(),last.getY()-1);
                if (!currentPath.visited.contains(next)){
                    visited = new ArrayList<>(currentPath.visited);
                    visited.add(next);
                    if (next.equals(end))
                        finalSolutions.add(new PartialSolution(visited,next));
                    else {
                        partialSolutions.add(new PartialSolution(visited,next));
                    }
                }
            }
        }
        System.out.println("There are " +finalSolutions.size()+" unique paths");
    }
    private static class PartialSolution{
        ArrayList<Position<Integer>> visited;
        Position<Integer> current;
        PartialSolution(ArrayList<Position<Integer>> visited,Position<Integer> current){
            this.current = current;
            this.visited = visited;
        }
    }
    private static class Position<A>{
        A x,y;
        Position(A x,A y){
            this.x = x;
            this.y = y;
        }
        public A getX() {
            return x;
        }
        public A getY() {
            return y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position<?> position = (Position<?>) o;
            if (!getX().equals(position.getX())) return false;
            return getY().equals(position.getY());
        }
        @Override
        public int hashCode() {
            int result = getX().hashCode();
            result = 31 * result + getY().hashCode();
            return result;
        }
    }
}
