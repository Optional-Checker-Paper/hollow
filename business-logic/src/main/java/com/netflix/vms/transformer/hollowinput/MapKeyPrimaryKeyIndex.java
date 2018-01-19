package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class MapKeyPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<VMSHollowInputAPI, MapKeyHollow> {

    public MapKeyPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, false);    }

    public MapKeyPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah) {
        this(consumer, isListenToDataRefreah, ((HollowObjectSchema)consumer.getStateEngine().getSchema("MapKey")).getPrimaryKey().getFieldPaths());
    }

    public MapKeyPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public MapKeyPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah, String... fieldPaths) {
        super(consumer, "MapKey", isListenToDataRefreah, fieldPaths);
    }

    public MapKeyHollow findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getMapKeyHollow(ordinal);
    }

}