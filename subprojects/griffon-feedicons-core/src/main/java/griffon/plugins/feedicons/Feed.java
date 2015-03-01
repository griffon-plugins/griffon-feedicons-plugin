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
package griffon.plugins.feedicons;

import javax.annotation.Nonnull;

import static griffon.util.GriffonClassUtils.requireState;
import static griffon.util.GriffonNameUtils.isBlank;
import static griffon.util.GriffonNameUtils.requireNonBlank;

/**
 * @author Andres Almiray
 */
public enum Feed {
    ACTIVITY_WINDOW("activity_window"),
    CHECK_ALL("check_all"),
    COMMENT_RSS("comment_rss"),
    COMMENT_RSS_ADD("comment_rss_add"),
    COMMENT_RSS_DELETE("comment_rss_delete"),
    COMMENT_RSS_INFO("comment_rss_info"),
    FEED_SOURECe("feed_source"),
    FEED("feed"),
    FOLDER("folder"),
    FOLDER_ADD("folder_add"),
    FOLDER_REMOVE("folder_remove"),
    FOLDER_RSS("folder_rss"),
    INFO("info"),
    MARK_AS_READ("mark_as_read"),
    NEXT_RSS("next_rss"),
    NEXT_UNREAD("next_unread"),
    POST_DELICIOUS("post_delicious"),
    POST_TO_BLOG("post_to_blog"),
    PREVIOUS_RSS("previous_rss"),
    PREVIOUS_UNREAD("previous_unread"),
    RELOAD("reload"),
    RSS("rss"),
    RSS_FILE("rss_file"),
    RSS_FILE_ADD("rss_file_add"),
    RSS_FILE_INFO("rss_file_info"),
    RSS_FILE_REMOVE("rss_file_remove"),
    RSS_PILL_BLUE("rss_pill_blue"),
    RSS_PILL_ORANGE("rss_pill_orange"),
    RSS_SITE("site"),
    RSS_SITE_RSS("site_rss"),
    STOP("stop"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private static final String ERROR_DESCRIPTION_BLANK = "Argument 'description' must not be blank";

    private final String description;

    Feed(@Nonnull String description) {
        this.description = description;
    }

    @Nonnull
    public String getDescription() {
        return description;
    }

    @Nonnull
    public String asResource(int size) {
        requireValidSize(size);
        return "com/zeusboxstudio/icons/" + size + "x" + size + "/" + description + ".png";
    }

    @Nonnull
    public static String asResource(@Nonnull String description) {
        int size = 16;
        checkDescription(description);

        String[] parts = description.split(":");
        if (parts.length == 2) {
            try {
                size = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw invalidDescription(description, e);
            }
        }

        Feed feed = findByDescription(description, size);
        return feed.asResource(size);
    }

    @Nonnull
    public static Feed findByDescription(@Nonnull String description) {
        checkDescription(description);

        Feed feed = null;
        String[] parts = description.split(":");
        for (Feed f : values()) {
            if (f.description.equalsIgnoreCase(parts[0])) {
                feed = f;
                break;
            }
        }

        if (feed == null) {
            throw invalidDescription(description);
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (parts.length == 2) {
            int size = 16;
            try {
                size = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw invalidDescription(description, e);
            }
            if (classLoader.getResource(feed.asResource(size)) != null) {
                return feed;
            }
        }

        if (classLoader.getResource(feed.asResource(16)) != null) {
            return feed;
        } else if (classLoader.getResource(feed.asResource(32)) != null) {
            return feed;
        }

        throw invalidDescription(description);
    }

    @Nonnull
    public static Feed findByDescription(@Nonnull String description, int size) {
        checkDescription(description);

        Feed feed = null;
        String[] parts = description.split(":");
        for (Feed f : values()) {
            if (f.description.equalsIgnoreCase(parts[0])) {
                feed = f;
                break;
            }
        }

        if (feed == null) {
            throw invalidDescription(description);
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader.getResource(feed.asResource(size)) != null) {
            return feed;
        }

        throw invalidDescription(description);
    }

    public static int requireValidSize(int size) {
        requireState(size == 16 || size == 32, "Argument 'size' must be either 16 or 32.");
        return size;
    }

    private static void checkDescription(String description) {
        if (isBlank(description)) {
            throw invalidDescription(description);
        }
    }

    @Nonnull
    public static IllegalArgumentException invalidDescription(@Nonnull String description) {
        requireNonBlank(description, ERROR_DESCRIPTION_BLANK);
        throw new IllegalArgumentException("Description " + description + " is not a valid Feed icon description");
    }

    @Nonnull
    public static IllegalArgumentException invalidDescription(@Nonnull String description, Exception e) {
        requireNonBlank(description, ERROR_DESCRIPTION_BLANK);
        throw new IllegalArgumentException("Description " + description + " is not a valid Feed icon description", e);
    }
}
