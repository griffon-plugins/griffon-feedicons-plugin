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
package griffon.swing.support.feedicons;

import griffon.plugins.feedicons.Feed;

import javax.annotation.Nonnull;
import javax.swing.ImageIcon;
import java.net.URL;

import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class FeedIcon extends ImageIcon {
    private final Feed feed;
    private final int size;

    public FeedIcon(@Nonnull Feed feed) {
        this(feed, 16);
    }

    public FeedIcon(@Nonnull Feed feed, int size) {
        super(toURL(feed, size));
        this.feed = feed;
        this.size = size;
    }

    public FeedIcon(@Nonnull String description) {
        this(Feed.findByDescription(description));
    }

    @Nonnull
    private static URL toURL(@Nonnull Feed feed, int size) {
        requireNonNull(feed, "Argument 'feed' must not be null.");
        String resource = feed.asResource(size);
        return Thread.currentThread().getContextClassLoader().getResource(resource);
    }

    @Nonnull
    public Feed getFeed() {
        return feed;
    }

    public int getSize() {
        return size;
    }
}
