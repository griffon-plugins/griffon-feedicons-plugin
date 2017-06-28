/*
 * Copyright 2014-2017 the original author or authors.
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
package griffon.javafx.support.feedicons;

import griffon.plugins.feedicons.Feed;
import javafx.scene.image.Image;

import javax.annotation.Nonnull;
import java.net.URL;

import static griffon.plugins.feedicons.Feed.invalidDescription;
import static griffon.util.GriffonNameUtils.requireNonBlank;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class FeedIcon extends Image {
    private static final String ERROR_FEED_NULL = "Argument 'feed' must not be null.";
    private Feed feed;
    private int size;

    public FeedIcon(@Nonnull Feed feed) {
        this(feed, 16);
    }

    public FeedIcon(@Nonnull Feed feed, int size) {
        super(toURL(feed, size), true);
        this.feed = requireNonNull(feed, ERROR_FEED_NULL);
        this.size = size;
    }

    public FeedIcon(@Nonnull String description) {
        super(toURL(description));
        feed = Feed.findByDescription(description);

        String[] parts = description.split(":");
        if (parts.length == 2) {
            try {
                size = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw invalidDescription(description, e);
            }
        } else {
            size = 16;
        }
    }

    @Nonnull
    private static String toURL(@Nonnull Feed feed, int size) {
        requireNonNull(feed, ERROR_FEED_NULL);
        String resource = feed.asResource(size);
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        if (url == null) {
            throw new IllegalArgumentException("Icon " + feed + ":" + size + " does not exist");
        }
        return url.toExternalForm();
    }

    @Nonnull
    private static String toURL(@Nonnull String description) {
        requireNonBlank(description, "Argument 'description' must not be blank");
        String resource = Feed.asResource(description);
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        if (url == null) {
            throw new IllegalArgumentException("Icon " + description + " does not exist");
        }
        return url.toExternalForm();
    }

    @Nonnull
    public Feed getFeed() {
        return feed;
    }

    public int getSize() {
        return size;
    }
}