package lapr4.red.s2.lang.n1150385.beanshell;

import bsh.EvalError;
import csheets.core.IllegalValueTypeException;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellInstance;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;

/**
 * Created by pyska on 6/7/17.
 */
public class BeanShellAsyncRunner implements Runnable {

    private final BeanShellInstance shellInstance;

    public BeanShellAsyncRunner(BeanShellInstance shellInstance){
        this.shellInstance = shellInstance;
    }

    @Override
    public void run() {
        try {
            shellInstance.setSynchronous();
            shellInstance.executeScript();
            shellInstance.setAsynchronous();
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        } catch (MacroCompilationException e) {
            e.printStackTrace();
        } catch (IllegalValueTypeException e) {
            e.printStackTrace();
        }
    }
}
