import kotlin.math.sqrt
fun main(args: Array<String>){
        if(args.size<3){
            println("Error: values for a, b, c required on command line")
            return System.exit(1)
        }
    var a = args[0].toDouble()
    var b = args[1].toDouble()
    var c = args[2].toDouble()
    val s = (a+b+c)/2.0
    val Area = sqrt(s*(s-a)*(s-b)*(s-c))
    println("Area=%.5f".format(area))
}
