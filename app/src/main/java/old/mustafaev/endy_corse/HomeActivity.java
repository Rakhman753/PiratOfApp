package old.mustafaev.endy_corse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import old.mustafaev.endy_corse.adapter.CategoryAdapter;
import old.mustafaev.endy_corse.adapter.CourseAdapter;
import old.mustafaev.endy_corse.model.Category;
import old.mustafaev.endy_corse.model.Course;

public class HomeActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullcourseList = new ArrayList<>();
    private ImageButton logout_btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Игры"));
        categoryList.add(new Category(2,"Сайты"));
        categoryList.add(new Category(3,"Языки"));
        categoryList.add(new Category(4,"Прочее"));

        setCategoryRecycler(categoryList);

        courseList.add(new Course(1,"java", "Профессия Java\nразработчик", "1 марта", "начальный", "#424345", "За программу вы изучите построение графических приложений под ПК, разработку веб сайтов на основе Java Spring Boot, изучите построение полноценных Андроид приложений и отлично изучите сам язык Java!", 3));
        courseList.add(new Course(2,"python", "Профессия Python\nразработчик", "10 марта", "начальный", "#9FA52D", "За программу вы изучите реализацию бэкенд-сервисов и архитектуры проектов, повышение отказоустойчивости и производительности ИТ-продуктов, участие в выводе новых продуктов на рынок и дальнейшем развитии", 3));
        courseList.add(new Course(3,"unity", "Профессия Unity\nразработчик", "15 марта", "начальный", "#34203F", "За программу вы изучите умение программировать на языке C#, разработка 2D и 3D игр на Unity 3D, умение работать с необходимыми фреймворками, умение создавать поведенческие и игровые сценарии", 1));
        courseList.add(new Course(4,"front_end", "Профессия Front-end\nразработчик", "20 марта", "начальный", "#7A3C4A", "За программу вы изучите умение работать с HTML и CSS и дизайн-макетами, верстка сайта и шаблонов под CMS, настройка всех блоков страниц, проверка, тестирование функционала, устранение багов, автоматизация проекта", 2));
        courseList.add(new Course(5,"back_end", "Профессия Back-end\nразработчик", "25 марта", "начальный", "#3C3064", "За программу вы изучите умение разработки программной логики проекта, его архитектуры, ядра всего проекта, отладку работы серверов и взаимодействия продукта с ними, интеграция с другими программами, приложениями", 2));
        courseList.add(new Course(6,"full_stack", "Профессия Full Stack\nразработчик", "30 марта", "начальный", "#007569", "За программу вы изучите создание архитектуры и ядро сайта, разработка функционала, тестирование, контроль версий", 2));

        fullcourseList.addAll(courseList);

        setCourseRecycler(courseList);

        logout_btn = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullcourseList);

        List<Course> filterCourses = new ArrayList<>();

        for(Course c: courseList){
            if(c.getCategory() == category)
                filterCourses.add(c);
        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }


}