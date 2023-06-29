package com.data.hope.annotation.valid.em;

/**
 * @author guoqinghua
 * @since 2022-03-01
 */
public interface EnumInterface<T> {

  /**
   * 判断是否存在
   * @param value
   * @return
   */
  Boolean exist(T value);
}
