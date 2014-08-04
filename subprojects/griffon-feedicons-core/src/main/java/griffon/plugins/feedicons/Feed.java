/*
 * Copyright 2014 the original author or authors.
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
    FLAG("feed"),
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
        requireState(size == 16 || size == 32, "Argument 'size' must be either 16 or 32.");
        return "com/zeusboxstudio/icons/" + size + "x" + size + "/" + description + ".png";
    }

    @Nonnull
    public static Feed findByDescription(@Nonnull String description) {
        if (isBlank(description)) {
            throw new IllegalArgumentException("Description " + description + " is not a valid Feed icon description");
        }

        for (Feed feed : values()) {
            if (feed.description.equalsIgnoreCase(description)) {
                return feed;
            }
        }
        throw new IllegalArgumentException("Description " + description + " is not a a valid Feed icon description");
    }
}
