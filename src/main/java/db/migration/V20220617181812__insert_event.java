package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V20220617181812__insert_event extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));

        byte[] rihanna = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/rihanna.jpg").readAllBytes();
        byte[] eminem = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/eminem.jpg").readAllBytes();
        byte[] oleg = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/oleg.jpg").readAllBytes();
        byte[] skydive = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/skydive.jpg").readAllBytes();
        byte[] matilda = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/matilda.jpg").readAllBytes();
        byte[] superCup = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/superCup.jpg").readAllBytes();
        byte[] luna = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/luna.jpg").readAllBytes();
        byte[] dune = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/dune.jpg").readAllBytes();
        byte[] laPerle = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/la-perle-by-dragone.jpeg").readAllBytes();
        byte[] hardkiss = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/hardkiss.jpg").readAllBytes();
        byte[] balloon = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/balloon.jpg").readAllBytes();
        byte[] okeanElzy = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/elzy.jpg").readAllBytes();
        byte[] dragons = this.getClass().getClassLoader()
                .getResourceAsStream("static/image/dragons.jpg").readAllBytes();

        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", rihanna, 1);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", eminem, 2);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", oleg, 3);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", skydive, 4);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", matilda, 5);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", superCup, 6);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", luna, 7);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", dune, 8);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", laPerle, 9);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", hardkiss, 10);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", balloon, 11);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", okeanElzy, 12);
        jdbcTemplate.update("UPDATE event SET image=? WHERE id=?", dragons, 13);
    }
}
