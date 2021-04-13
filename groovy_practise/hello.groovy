class Example 
{
    static void main(String[] args)
    {
        /* println 'test data'
        println "hello world"

        def x = 1;
        def str1 = 'i am x = $x '
        def str2 = "i am x = $x "
        println str1
        println str2 */

        def list = [2,'eat',true]
        list[4] = 'tt';
        for(a in list) 
        {
            println " $a "
        }
    }
}