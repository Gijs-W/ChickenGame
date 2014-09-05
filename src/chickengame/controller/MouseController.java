/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chickengame.controller;

import chickengame.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Gijs
 */
public class MouseController extends MouseAdapter implements IObservable {
    
    private ArrayList<Observer> observers;
    
    private MouseCoordinate coordinate;
    

    public MouseController() {
        observers = new ArrayList<>();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        coordinate = new MouseCoordinate(e.getX(), e.getY());
        notifyObservers();
    }
    

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(null, coordinate);
        }
    }
    
}
