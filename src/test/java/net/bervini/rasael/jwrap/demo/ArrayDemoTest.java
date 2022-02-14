/*
 * Copyright 2022-2022 Rasael Bervini
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

package net.bervini.rasael.jwrap.demo;

import net.bervini.rasael.jwrap.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class ArrayDemoTest {

  private static Console console;

  @BeforeAll
  static void setup() {
    console = new Console();
  }

  @Test
  void demo() {

    var array = $(new String[]{"1","2","b"});
    console.log(array.size());

    // null arrays always have size of 0
    console.log($((String[])null).size());

    // array always represent themselves naturally
    console.log(array);

    // you can access an array element using 'get()'
    console.log(array.get(2));

    // and change elements using 'set()'
    array.set(0, "a")
         .set(1, "c");
    console.log(array);

    // elements in an array can be swapped
    array.swap(1,2);
    console.log(array);

    // array can be iterated with forEach
    array.forEach(console::log);

    // or normal for-each loops
    for (String element : array) {
      console.log(element);
    }

    // you can get elements on an array using at()
    console.log(array.at(-1));
    console.log(array.at(-2));

    // you can add an item to an array using push()
    array.push("hello");
    console.log(array);

    // or remove it using 'pop'
    array.pop();
    console.log(array);

    // You can pop over the array size
    var last = array.pop(); // remove 'c'
    console.log(last);

    array.pop(); // remove 'b'
    array.pop(); // remove 'a'
    array.pop(); // nop
    array.pop(); // nop
    array.shift(); // nop
    console.log(array);

    array.push("Apple","Banana");
    console.log(array);

    var first = array.shift();
    console.log(first);
    console.log(array);

    array.unshift("Strawberry");
    console.log(array);

    array.push("Mango");
    console.log(array);

    var pos = array.indexOf("Banana");
    console.log(pos); // 1

    array.splice(pos, 1);
    console.log(array); // Straberry, Mango

    var removed = Lists.<String>newList();
    array.spliceTo(removed, 0, 1);
    console.log(removed); // Strawberry
    console.log(array); // Mango


    var javaVegiArray = new String[]{"Cabbage", "Turnip", "Radish", "Carrot"};
    var vegetables = $(javaVegiArray);

    console.log(vegetables);
// ["Cabbage", "Turnip", "Radish", "Carrot"]

    pos = 1;
    var n = 2;

    var removedItems = Lists.<String>newList();

    vegetables.spliceTo(removedItems, pos, n);
// this is how to remove items, n defines the number of items to be removed,
// starting at the index position specified by pos and progressing toward the end of array.

    console.log(vegetables);
// ["Cabbage", "Carrot"] (the original array is changed)

    console.log(removedItems);
// ["Turnip", "Radish"]

    console.log(vegetables.json());

    vegetables.forEachEntry((i,e) -> {
      console.log(i + ", " + e);
    });

    System.out.println(console);
    checkConsole();
  }

  private void checkConsole() {
    assertThat(console)
        .hasToString("""
            3
            0
            [1, 2, b]
            b
            [a, c, b]
            [a, b, c]
            a
            b
            c
            a
            b
            c
            c
            b
            [a, b, c, hello]
            [a, b, c]
            c
            []
            [Apple, Banana]
            Apple
            [Banana]
            [Strawberry, Banana]
            [Strawberry, Banana, Mango]
            1
            [Strawberry, Mango]
            [Strawberry]
            [Mango]
            [Cabbage, Turnip, Radish, Carrot]
            [Cabbage, Carrot]
            [Turnip, Radish]
            ["Cabbage","Carrot"]
            0, Cabbage
            1, Carrot""");
  }


}
