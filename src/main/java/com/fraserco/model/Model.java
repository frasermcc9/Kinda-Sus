// Copyright 2020 Fraser
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.fraserco.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {
    public static String[] colours;
    private static Model instance;

    static {
        Model.colours = new String[]{"#e31414", "#0f39f7", "#0a910a", "#db1aa1", "#ff8c00", "#f0d90e",
                "#858585", "#e6e6e6", "#9e1adb", "#4d1f00", "#0ff7e4", "#11f715"};
    }

    private Map<Integer, String> colourMap = null;

    private Model() {

    }

    public static Model get() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void uploadMap(Map<Integer, String> map) {
        this.colourMap = map;
    }

    public List<String> getColours() {
        return new ArrayList<>(this.colourMap.values());
    }
}
