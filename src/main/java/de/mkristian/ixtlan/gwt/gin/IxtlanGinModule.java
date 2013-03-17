package de.mkristian.ixtlan.gwt.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.GinModule;

public class IxtlanGinModule extends AbstractGinModule {
    
    private final GinModule[] modules;
    
    protected IxtlanGinModule( GinModule... modules ){
        this.modules = modules;
    }
    
    @Override
    protected void configure() {
        for( GinModule module : this.modules ){
            install( module );
        }
    }
}