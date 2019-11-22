package dev.danieluac.toknowing.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.danieluac.toknowing.Home;
import dev.danieluac.toknowing.Resource.SomeQuestion;

public class QuestionTable extends DataBaseHelper {

    private  int Id;
    private String Resposta;
    private String Pergunta;
    private String Opcao1;
    private String Opcao2;
    private String Opcao3;
    private final String  QTABLE = "QUESTION";
    private final String COL_ID = "ID";
    private final String COL_PERGUNTA = "PERGUNTA";
    private final String COL_1 = "OPCAO1";
    private final String COL_2 = "OPCAO2";
    private final String COL_3 = "OPCAO3";
    private final String COL_RESPOSTA = "RESPOSTA";
    private  Context context;
    private SomeQuestion someQuestion;
    private List<QuestionTable> CquestionTableList;
    public QuestionTable(Context context){
        super(context);
        this.context = context;

        //CquestionTableList = getAllData();
        if(SelectData().getCount()<=0)
        {
            someQuestion = new SomeQuestion(this.context);
        }
    }
    public QuestionTable(Context context,int id){
        super(context);
        setId(id);
        this.context = context;
    }
    public QuestionTable(Context context,String pergunta, String opcao1, String opcao2, String opcao3,String resposta) {
        super(context);
        this.context = context;
        setPergunta(pergunta);
        setOpcao1(opcao1);
        setOpcao2(opcao2);
        setOpcao3(opcao3);
        setResposta(resposta);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        CreateTable(db);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        DropTable(db);
        onCreate(db);
    }
    public Boolean InsertData()
    {
       if (getPergunta().isEmpty() || getOpcao1().isEmpty() || getOpcao2().isEmpty()
               || getOpcao3().isEmpty() || getResposta().isEmpty())
           return false;
       else {
           ContentValues contentValues = new ContentValues();
           contentValues.put(COL_PERGUNTA,getPergunta());
           contentValues.put(COL_1,getOpcao1());
           contentValues.put(COL_2,getOpcao2());
           contentValues.put(COL_3,getOpcao3());
           contentValues.put(COL_RESPOSTA,getResposta());
           SQLiteDatabase db =  this.getWritableDatabase();
           Long result = this.Connection.insert(QTABLE, null,contentValues);
           if (result == -1)
               return false;
           else
               return true;
       }
    }
    private Boolean UpdateData()
    {
        if (getPergunta().isEmpty() || getOpcao1().isEmpty() || getOpcao2().isEmpty()
                || getOpcao3().isEmpty() || getResposta().isEmpty())
            return false;
        else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_PERGUNTA,getPergunta());
            contentValues.put(COL_1,getOpcao1());
            contentValues.put(COL_2,getOpcao2());
            contentValues.put(COL_3,getOpcao3());
            contentValues.put(COL_RESPOSTA,getResposta());

           return true;
        }
    }
    public List<QuestionTable> getAllData()
    {
        List<QuestionTable> questionTableList = new ArrayList<>();
        Cursor result =SelectData();

        if (result.moveToFirst()) {
            do {
                QuestionTable questionTable = new QuestionTable(this.context);

                questionTable.setId(Integer.parseInt(result.getString(0)));

                questionTable.setPergunta(result.getString(1));

                questionTable.setOpcao1(result.getString(2));
                questionTable.setOpcao2(result.getString(3));
                questionTable.setOpcao3(result.getString(4));
                questionTable.setResposta(result.getString(5));

                questionTableList.add(questionTable);

            } while (result.moveToNext());
        }
            result.close();
        return questionTableList;
    }
    private Cursor SelectData()
    {
        Cursor result;

        if(getId()!=0)
        {

            result = Connection.rawQuery("SELECT * FROM "+QTABLE+
                    " WHERE "+COL_ID+"='"+Integer.toString(getId())+"';",null);
            return result;
        }else
        {
            result = Connection.rawQuery("SELECT * FROM "+QTABLE+";",null);
            return result;
        }
    }
    private void CreateTable (SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                QTABLE+"(" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_PERGUNTA+" VARCHAR(255)," +
                COL_1+" VARCHAR(255)," +
                COL_2+" VARCHAR(255)," +
                COL_3+" VARCHAR(255)," +
                COL_RESPOSTA+" VARCHAR(255)" +
                ")");
    }
    private void DropTable (SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+QTABLE);
    }
    public int getId() {
        return Id;
    }

    public QuestionTable setId(int id) {
        Id = id;
        return this;
    }

    public String getResposta() {
        return Resposta;
    }

    public void setResposta(String resposta) {
        Resposta = resposta;
    }

    public String getPergunta() {
        return Pergunta;
    }

    public void setPergunta(String pergunta) {
        Pergunta = pergunta;
    }

    public String getOpcao1() {
        return Opcao1;
    }

    public void setOpcao1(String opcao1) {
        Opcao1 = opcao1;
    }

    public String getOpcao2() {
        return Opcao2;
    }

    public void setOpcao2(String opcao2) {
        Opcao2 = opcao2;
    }

    public String getOpcao3() {
        return Opcao3;
    }

    public void setOpcao3(String opcao3) {
        Opcao3 = opcao3;
    }
}
