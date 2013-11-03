package de.mkristian.ixtlan.gwt.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SimpleKeyProvider;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.FormListEntry;
import com.googlecode.mgwt.ui.client.widget.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class ValueCheckBoxes<T> 
        extends Composite
        implements IsEditor<TakesValueEditor<List<T>>>,
                   TakesValue<List<T>>,
                   HasValueChangeHandlers<List<T>> 
    {

    private final List<T> values = new ArrayList<T>();
    private final Map<Object, Integer> valueKeyToIndex = new HashMap<Object, Integer>();
    private final Renderer<T> renderer;
    private final ProvidesKey<T> keyProvider;

    private TakesValueEditor<List<T>> editor;
    protected final List<T> selectedValues = new LinkedList<T>();
    private ValueChangeHandler<Boolean> handler;
    private boolean enabled;

    static interface BoxWithKey extends HasValue<Boolean>, IsWidget {
        
        public void setEnabled(boolean enabled);
        
        public Object getKey();
    }
    
    static class CheckBoxWithKey extends CheckBox implements BoxWithKey {

        private final Object key;

        CheckBoxWithKey( Object key ){
            this.key = key;
        }
        
        @Override
        public Object getKey()
        {
            return key;
        }    
    }

    static class MCheckBoxWithKey extends MCheckBox implements BoxWithKey {

        private final Object key;

        MCheckBoxWithKey( Object key ){
            this.key = key;
        }
        
        @Override
        public Object getKey()
        {
            return key;
        }

        @Override
        public void setEnabled( boolean enabled )
        {
            setReadOnly( !enabled );
        }
    }
    
    static class CheckBoxItem extends FormListEntry
            implements HasValueChangeHandlers<Boolean>{

        private final BoxWithKey box;

        private CheckBoxItem( String label,
                              BoxWithKey box ) {
            super( label, (Widget)box);
            box.setValue( false );
            this.box = box;
        }

        @Override
        public HandlerRegistration addValueChangeHandler( ValueChangeHandler<Boolean> handler )
        {
            return box.addValueChangeHandler( handler );
        }
    }
    
    public ValueCheckBoxes(Renderer<T> renderer) {
        this( renderer, new SimpleKeyProvider<T>(), new WidgetList() );
    }

    public <S extends Widget & HasWidgets> ValueCheckBoxes( Renderer<T> renderer, 
                                                            S container ) {
        this( renderer, new SimpleKeyProvider<T>(), container);
    }

    public <S extends Widget & HasWidgets> ValueCheckBoxes( Renderer<T> renderer,
                                                            ProvidesKey<T> keyProvider, 
                                                            S container ) {
        this.keyProvider = keyProvider;
        this.renderer = renderer;
        initWidget( container );
        
        handler = new ValueChangeHandler<Boolean>() {

            public void onValueChange(ValueChangeEvent<Boolean> event) {
                Object key = ((BoxWithKey) event.getSource()).getKey();
                T value = values.get(valueKeyToIndex.get(key));
                if (event.getValue()) {
                    int index = selectedValues.indexOf(value);
                    if (index < 0) {
                        selectedValues.add(value);
                    }
                } else {
                    selectedValues.remove(value);
                }
            }
        };
    }
    
    @Override
    public HandlerRegistration addValueChangeHandler(
            ValueChangeHandler<List<T>> handler) {
        return addHandler( handler, ValueChangeEvent.getType() );
    }

    /**
     * Returns a {@link TakesValueEditor} backed by the ValueCheckBoxes.
     */
    public TakesValueEditor<List<T>> asEditor() {
        if (editor == null) {
            editor = TakesValueEditor.of( this );
        }
        return editor;
    }

    public List<T> getValue() {
        return Collections.unmodifiableList( selectedValues );
    }

    public void setAcceptableValues(Collection<T> newValues) {
        values.clear();
        valueKeyToIndex.clear();
        getContainer().clear();

        for (T nextNewValue : newValues) {
            addValue(nextNewValue);
        }
        
        if(values.size() == 1){
            selectedValues.clear();
            selectedValues.addAll(values);
        }
        
        updateList();
        setEnabled(enabled);
    }

    /**
     * Set the value and display it in the select element. Add the value to the
     * acceptable set if it is not already there.
     */
    public void setValue(List<T> values) {
        setValue(values, false);
    }

    public void setValue(List<T> values, boolean fireEvents) {
        if ( this.selectedValues.equals(values)) {
            return;
        }

        List<T> before = new ArrayList<T>(this.selectedValues);
        this.selectedValues.clear();
        if( values != null ){
            this.selectedValues.addAll(values);
        }

        updateList();

        if (fireEvents) {
            ValueChangeEvent.fireIfNotEqual(this, before, values);
        }
    }
    
    public void selectAll(){
        setValue( this.values );
    }

    protected void addValue(T value) {
        Object key = keyProvider.getKey(value);
        if (valueKeyToIndex.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate value: " + value);
        }

        valueKeyToIndex.put(key, values.size());
        values.add(value);

        CheckBoxItem box = newCheckBoxItem(value, key);
        box.addValueChangeHandler(handler);
        getContainer().add( box.asWidget() );
        // TODO remove ?
    //    assert values.size() == getInsertPanel().getWidgetCount();
    }
    
    protected CheckBoxItem newCheckBoxItem(T value, Object key){
        if (!MGWT.getOsDetection().isDesktop() ){
            return new CheckBoxItem( renderer.render( value ), new CheckBoxWithKey( key ) );
        }
        else {
            return new CheckBoxItem( renderer.render( value ), new MCheckBoxWithKey( key ) );
        }
    }

    private HasWidgets getContainer() {
        return (HasWidgets) getWidget();
    }

    private void updateList() {
        List<T> newSelected = new ArrayList<T>(this.selectedValues);
        newSelected.removeAll(this.values);

        for (T value : newSelected) {
            addValue(value);
        }

        int index = 0;
        Iterator<Widget> iter = getContainer().iterator();
        while( iter.hasNext() ){
            HasValue<Boolean> box = ((CheckBoxItem) iter.next()).box;
            box.setValue( selectedValues.contains( values.get( index++ ) ) );
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        Iterator<Widget> iter = getContainer().iterator();
        while( iter.hasNext() ){
            Widget next = iter.next();
            ((CheckBoxItem) next ).box.setEnabled( enabled );
        }
    }
    
    public void setReadonly( boolean readonly ){
        setEnabled( !readonly );
    }
}
