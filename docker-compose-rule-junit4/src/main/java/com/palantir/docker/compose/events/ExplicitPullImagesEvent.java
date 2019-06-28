/*
 * (c) Copyright 2019 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.docker.compose.events;

import org.immutables.value.Value;

public interface ExplicitPullImagesEvent extends DockerComposeRuleEvent {

    @Value.Immutable
    interface Started extends ExplicitPullImagesEvent, LifeCycleEvent.Started { }

    @Value.Immutable
    interface Succeeded extends ExplicitPullImagesEvent, LifeCycleEvent.Succeeded { }

    @Value.Immutable
    interface Failed extends ExplicitPullImagesEvent, LifeCycleEvent.Failed { }

    LifeCycleEvent.Factory2 FACTORY = new LifeCycleEvent.Factory2() {
        @Override
        public LifeCycleEvent.Started started() {
            return ImmutableStarted.builder().build();
        }

        @Override
        public LifeCycleEvent.Succeeded succeeded() {
            return ImmutableSucceeded.builder().build();
        }

        @Override
        public LifeCycleEvent.Failed failed(Exception exception) {
            return ImmutableFailed.of(exception);
        }
    };
}