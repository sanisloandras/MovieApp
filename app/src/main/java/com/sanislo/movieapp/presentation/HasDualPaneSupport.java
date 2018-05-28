package com.sanislo.movieapp.presentation;

public interface HasDualPaneSupport {
    public boolean isInDualPaneMode();
    public int getLeftContainerId();
    public int getRightContainer();
}
