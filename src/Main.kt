/**
 * Created by bhags on 2016/11/15.
 */


fun main(args: Array<String>) {
    permutation("123456789ab")
}

fun permutation(str: String) {
    permutation("", str)
}

private fun permutation(prefix: String, str: String) {
    val n = str.length
    if (n == 0)
        println(prefix)
    else {
        for (i in 0..n - 1)
            permutation(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
    }
}