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
package griffon.swing.support.feedicons

import griffon.plugins.feedicons.Feed
import spock.lang.Specification

/**
 * @author Andres Almiray
 */
class FeedIconSpec extends Specification {
    def 'Can create a FeedIcon instance'() {
        given:
        Feed expected = Feed.ACTIVITY_WINDOW

        expect:
        FeedIcon icon = new FeedIcon(expected)
        icon.feed == expected
        icon.size == 16
    }

    def 'Invalid FeedIcon arguments'() {
        when:
        new FeedIcon(arg)

        then:
        thrown(IllegalArgumentException)

        where:
        arg   | _
        null  | _
        ''    | _
        ' '   | _
        'foo' | _
    }
}
