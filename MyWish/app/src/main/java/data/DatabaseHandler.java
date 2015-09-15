package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Wish;

/**
 * Created by Administrator on 14/09/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wishes.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_WISH = "tb_wish";
    private static final String KEY_ID_WISH = "id";
    private static final String KEY_TITLE_WISH = "title";
    private static final String KEY_CONTENT_WISH = "content";
    private static final String KEY_DATE_WISH = "date";
    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Tạo bảng CSDL
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_WISH = "CREATE TABLE " + TABLE_WISH + "("
                + KEY_ID_WISH + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_TITLE_WISH + " TEXT,"
                + KEY_CONTENT_WISH + " TEXT,"
                + KEY_DATE_WISH + " LONG" + ")";
        db.execSQL(CREATE_TABLE_WISH);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISH);
        onCreate(db);
    }

    public void insertWish(Wish wish){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE_WISH, wish.getTitle());
        values.put(KEY_CONTENT_WISH, wish.getContent());
        values.put(KEY_DATE_WISH, System.currentTimeMillis());
        if((db.insert(TABLE_WISH, null, values) != -1)){
            Toast.makeText(context, "Add complete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Add fail", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public ArrayList<Wish> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Wish> listWish = new ArrayList<Wish>();
        String sql = "SELECT * FROM " + TABLE_WISH;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        // Nếu chưa cursor chưa cuối thì tiếp tục chạy rồi add data vào ArrayList
        while(!cursor.isAfterLast()){
            Wish wish = new Wish();
            wish.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_WISH)));
            wish.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE_WISH)));
            wish.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT_WISH)));
            // Format lại định dạng
            DateFormat dateFormat = DateFormat.getDateInstance();
            String date = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(KEY_DATE_WISH))).getTime());
            wish.setDate(date);
            listWish.add(wish);
            cursor.moveToNext();

        }
        db.close();
        return listWish;

    }

    public void deleteWish(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WISH, KEY_ID_WISH + " = ? ", new String []{String.valueOf(id)});
        db.close();
    }


}
