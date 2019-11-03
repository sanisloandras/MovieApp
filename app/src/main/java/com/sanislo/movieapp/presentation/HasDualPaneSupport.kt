package com.sanislo.movieapp.presentation

interface HasDualPaneSupport {
    fun isInDualPaneMode(): Boolean
    fun leftContainerId(): Int
    fun rightContainer(): Int
}
