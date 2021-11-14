package org.jiangxin.gpu;


import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.types.Row;

import org.jiangxin.gpu.udf.NativeUdf;

import java.time.LocalDate;

public class GpuOnFlink {

  public static void main(String[] args) {
      final EnvironmentSettings settings =
              EnvironmentSettings.newInstance().useBlinkPlanner().inBatchMode().build();
      final TableEnvironment env = TableEnvironment.create(settings);

      final Table customers =
              env.fromValues(
                      DataTypes.of("ROW<name STRING, order_date DATE, item_count INT>"),
                      Row.of("Guillermo Smith", LocalDate.parse("2020-12-01"), 3),
                      Row.of("Guillermo Smith", LocalDate.parse("2020-12-05"), 5),
                      Row.of("Valeria Mendoza", LocalDate.parse("2020-03-23"), 4),
                      Row.of("Valeria Mendoza", LocalDate.parse("2020-06-02"), 10),
                      Row.of("Leann Holloway", LocalDate.parse("2020-05-26"), 9),
                      Row.of("Leann Holloway", LocalDate.parse("2020-05-27"), null),
                      Row.of("Brandy Sanders", LocalDate.parse("2020-10-14"), 1),
                      Row.of("John Turner", LocalDate.parse("2020-10-02"), 12),
                      Row.of("Ellen Ortega", LocalDate.parse("2020-06-18"), 100)
              );
      env.createTemporaryView("customers", customers);
      env.createTemporaryFunction("NativeUdf", NativeUdf.class);

      env.executeSql(
              "SELECT name, NativeUdf(name) "
                      + "FROM customers")
              .print();
  }
}