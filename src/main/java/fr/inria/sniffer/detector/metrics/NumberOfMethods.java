/**
 *   Sniffer - Analyze the history of Android code smells at scale.
 *   Copyright (C) 2019 Sarra Habchi
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package fr.inria.sniffer.detector.metrics;

import fr.inria.sniffer.detector.entities.Entity;

/**
 * Created by Geoffrey Hecht on 20/05/14.
 */
public class NumberOfMethods extends UnaryMetric<Integer> {

    private NumberOfMethods(Entity entity, int value) {
        this.value = value;
        this.entity = entity;
        this.name = "number_of_methods";
    }

    public static NumberOfMethods createNumberOfMethods(Entity entity, int value) {
        NumberOfMethods numberOfMethods = new NumberOfMethods(entity, value);
        numberOfMethods.updateEntity();
        return numberOfMethods;
    }
}
