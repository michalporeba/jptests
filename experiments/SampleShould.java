public class SampleShould
{
    public void be_a_test()
    {
        System.out.println("A");
    }

    public void be_another_test()
    {
        System.out.println("B");
    }

    public int not_be_a_test_as_it_has_non_void_return_type()
    {
        return 0;
    }

    public void not_be_a_test_as_it_has_parameters(int a)
    {

    }

    private void this_is_private()
    {
        
    }
}