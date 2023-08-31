package component.architecture.app_package

import component.util.repositoryName

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createRepositoryKt(packageName: String): String {
    val repositoryName = packageName.repositoryName()

    val sb = StringBuilder()
    sb.append(
        """
package $packageName.data.repository;

import androidx.annotation.VisibleForTesting;

import com.zondy.lib.core.architecture.BaseModel;
import $packageName.data.source.IDBDataSource;
import $packageName.data.source.ILocalDataSource;
import $packageName.data.source.INetDataSource;
import $packageName.data.source.db.DBDataSourceImpl;
import $packageName.data.source.local.LocalDataSourceImpl;
import $packageName.data.source.net.NetDataSourceImpl;

public class $repositoryName extends BaseModel implements INetDataSource, ILocalDataSource, IDBDataSource {
   
    private volatile static $repositoryName INSTANCE = null;
    private final INetDataSource mNetDataSource;
    private final ILocalDataSource mLocalDataSource;
    private final IDBDataSource mDBDataSource;

    private $repositoryName() {
        this.mNetDataSource = new NetDataSourceImpl();
        this.mLocalDataSource = new LocalDataSourceImpl();
        this.mDBDataSource = new DBDataSourceImpl();
    }

    public static $repositoryName getInstance() {
        if (INSTANCE == null) {
            synchronized ($repositoryName.class) {
                if (INSTANCE == null) {
                    INSTANCE = new $repositoryName();
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
    """
    )
    return sb.toString()
}