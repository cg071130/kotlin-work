// COMP2850 Portfolio: Week 2
// Function to redact sensitive information in a string
fun redact(input: String,target: String,replacement: Char = 'X'): String{
    val mask = replacement.toString().repeat(target.length)
    return input.replace(target,mask)
}
