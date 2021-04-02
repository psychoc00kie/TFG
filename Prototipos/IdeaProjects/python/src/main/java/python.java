import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class python {


        public static void main(String[] args) throws PyException {
            PythonInterpreter interp = new PythonInterpreter();
            System.out.println("executing test py");
            interp.set("courrier_id","1111");
            interp.execfile("test.py");
            PyObject state = interp.get("scann");
            boolean _state = Py.py2boolean(state);

            System.out.println(state);

            //System.out.println("Loading GreenHouse Script");
            //interp.execfile("Schedule.ghs");
            //System.out.println("Executing GreenHouse Script");
            //interp.exec("run()");
        }

}
