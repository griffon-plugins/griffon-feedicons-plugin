/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.builder.javafx.factory

import griffon.javafx.support.feedicons.FeedIcon
import griffon.plugins.feedicons.Feed
import groovyx.javafx.factory.LabeledFactory

/**
 * @author Andres Almiray
 */
public class FeedIconFactory extends LabeledFactory {
    FeedIconFactory() {
        super(FeedIcon)
    }

    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        def icon = attributes.remove('icon') ?: value
        int size = attributes.remove('size') ?: 16

        if (icon instanceof Feed) {
            return new FeedIcon(icon, size)
        } else if (icon instanceof CharSequence) {
            return new FeedIcon(Feed.findByDescription(icon.toString()), size)
        }

        throw new IllegalArgumentException("In $name you must define a node value or icon:")
    }
}
