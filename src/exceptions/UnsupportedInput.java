/**
 * @name UnsupportedInput
 * @package exceptions
 * @file UnsupportedInput.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description self defined exception. It executes when you give wrong inputs.
 *              Because it inherits from IllegalArgumentException, the programm will stop when the exception is executed.
 */

package exceptions;

public class UnsupportedInput extends IllegalArgumentException {

    /**
     * the constructor only takes the string (which is an error message) as argument.
     */
    public UnsupportedInput(String s) {
        super(s);
    }
}
