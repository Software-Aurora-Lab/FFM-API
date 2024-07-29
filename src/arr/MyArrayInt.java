package arr;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.List;
import java.util.Vector;

public class MyArrayInt {

    private final MemorySegment segment;
    private int size;
    private int index;
    private List<MemorySegment> list;
    

    public MyArrayInt(int size) throws Throwable {
        this.size = size;
        this.index = 0;
        Arena arena = Arena.ofConfined();
        this.segment = arena.allocate(ValueLayout.JAVA_INT.byteSize() * this.size);
        this.list = new Vector<>();
    }

    public void setValue(int val) {
        this.segment.set(ValueLayout.JAVA_INT, 0, val);
        this.list.add(this.segment);
        ++this.index;
    }

    public void visit(int idx) {
        if (idx > this.index) {
            System.out.println("out of range");
        } 
        else {
            System.out.println(this.list.get(idx));
        }
    }

    public List<MemorySegment> get() {
        System.err.println(segment.get(ValueLayout.JAVA_INT, 0));
        return this.list;
    }

    

    

}
