/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.endpoints.fakes;

import lombok.Data;

@Data
public class UserNodeFake {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String email;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String zip;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String city;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String username;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String firstName;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String lastName;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String externalUserId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String title;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String url;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String company;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String tel;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String telMobile;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String avatar;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String address;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String fullName;
}
