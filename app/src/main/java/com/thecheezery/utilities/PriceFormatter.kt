package com.thecheezery.utilities

fun Double.asPrice(): String =
    if (this % 1.0 == 0.0) toInt().toString() else "%.2f".format(this)
