fun gcf(n1:Int, n2:Int, n3:Int):Int {
    var gcd = 1
    var i = 1
    while (i <= n1 && i <= n2 && i <= n3) {
        if ((n1 % i == 0) && (n2 % i == 0) && (n3 % i == 0) ) gcd = i
        ++i
    }
    return gcd
}