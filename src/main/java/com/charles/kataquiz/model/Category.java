/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.model;

/**
 * Represents a trivia category from the Open Trivia Database.
 *
 * @param id the category ID
 * @param name the category name
 */
public record Category(int id, String name) {

    /**
     * Returns the category name.
     *
     * @return the category name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
