package com.example.loginimplenetation.databinding;
import com.example.loginimplenetation.R;
import com.example.loginimplenetation.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TwoLayoutCombinedBindingImpl extends TwoLayoutCombinedBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"item_layout", "confirm_update_item"},
            new int[] {1, 2},
            new int[] {com.example.loginimplenetation.R.layout.item_layout,
                com.example.loginimplenetation.R.layout.confirm_update_item});
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public TwoLayoutCombinedBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private TwoLayoutCombinedBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.example.loginimplenetation.databinding.ItemLayoutBinding) bindings[1]
            , (com.example.loginimplenetation.databinding.ConfirmUpdateItemBinding) bindings[2]
            );
        setContainedBinding(this.a);
        setContainedBinding(this.b);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        a.invalidateAll();
        b.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (a.hasPendingBindings()) {
            return true;
        }
        if (b.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        a.setLifecycleOwner(lifecycleOwner);
        b.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeB((com.example.loginimplenetation.databinding.ConfirmUpdateItemBinding) object, fieldId);
            case 1 :
                return onChangeA((com.example.loginimplenetation.databinding.ItemLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeB(com.example.loginimplenetation.databinding.ConfirmUpdateItemBinding B, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeA(com.example.loginimplenetation.databinding.ItemLayoutBinding A, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(a);
        executeBindingsOn(b);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): b
        flag 1 (0x2L): a
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}