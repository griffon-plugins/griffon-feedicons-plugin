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
package griffon.plugins.feedicons

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Andres Almiray
 */
class FeedSpec extends Specification {
    @Unroll("Description '#desc' is invalid")
    def "Invalid feed description"() {
        when:
        Feed.findByDescription(desc)

        then:
        thrown(IllegalArgumentException)

        where:
        desc  | _
        null  | _
        ''    | _
        ' '   | _
        'foo' | _
    }

    @Unroll('Icon #icon with size #size can be resolved')
    def 'Validate all Feed icons and sizes'() {
        expect:
        feed.asResource(size)

        where:
        feed << (Feed.values() + Feed.values())
        size << ([16] * Feed.values().size()) + ([32] * Feed.values().size())
    }
}
