package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static ProjectDataRepository provideProjectDataSource(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new ProjectDataRepository(database.mProjectDao());
    }

    public static TaskDataRepository provideTaskDataSource(Context context) {
        TodocDatabase database = com.cleanup.todoc.database.dao.TodocDatabase.getInstance(context);
        return new TaskDataRepository(database.mTaskDao());
    }

    public static Executor provideExecutor() { return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ProjectDataRepository dataSourceProject = provideProjectDataSource(context);
        TaskDataRepository dataSourceTask = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceProject, dataSourceTask, executor);
    }

}
