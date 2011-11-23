package com.adc.pdfer.stripes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import net.sourceforge.stripes.action.ActionBeanContext;


public class BaseAction implements net.sourceforge.stripes.action.ActionBean{

    protected AppContext myContext;

    public void setContext(ActionBeanContext arg0) {
        myContext = (AppContext)arg0;
    }

    public ActionBeanContext getContext() {
        return myContext;
    }

}
