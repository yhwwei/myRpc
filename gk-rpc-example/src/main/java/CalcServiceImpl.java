/**
 * @author yhw
 * @version 1.0
 **/
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }
}
