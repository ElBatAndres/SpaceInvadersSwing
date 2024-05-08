import co.edu.uptc.model.GameManagement;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.utils.ConfigManager;
import co.edu.uptc.view.MainBoard;

public class Main {
    public static void main(String[] args) {
        new ConfigManager();
        Presenter presenter = new Presenter();
        GameManagement model = new GameManagement();
        MainBoard view = new MainBoard();

        presenter.setView(view);
        presenter.setModel(model);

        model.setPresenter(presenter);

        view.setPresenter(presenter);
        view.begin();
    }
}