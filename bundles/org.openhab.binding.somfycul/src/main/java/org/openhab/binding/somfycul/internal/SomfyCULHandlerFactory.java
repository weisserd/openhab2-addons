/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.somfycul.internal;

import static org.openhab.binding.somfycul.internal.SomfyCULBindingConstants.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link SomfyCULHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Daniel Weisser - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.somfycul", service = ThingHandlerFactory.class)
public class SomfyCULHandlerFactory extends BaseThingHandlerFactory {

    // TODO Split to two handlers (like PulseAudio)
    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections
            .unmodifiableSet(Stream.of(CUL_DEVICE_THING_TYPE, SOMFY_DEVICE_THING_TYPE).collect(Collectors.toSet()));

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(CUL_DEVICE_THING_TYPE) && thing instanceof Bridge) {
            return new CULHandler((Bridge) thing);
        } else if (thingTypeUID.equals(SOMFY_DEVICE_THING_TYPE)) {
            return new SomfyCULHandler(thing);
        }

        return null;
    }
}
