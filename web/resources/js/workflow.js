/* Workflow *******************************************************************/

function Workflow(name, tasks) {
    this.componentType = "workflow";
    this.id = guid();
    this.name = name || "";
    this.tasks = tasks || new Array();
};

Workflow.prototype.addTask = function (task) {
    this.tasks.push(task);
    task.workflow = this;
};

Workflow.prototype.removeTask = function (task) {
    task.workflow = null;
    var index = -1;
    
    this.tasks.forEach(function (e, i) {
        if (e.id === task.id) {
            index = i;
            return true;
        }
    });
    
    if (index !== -1) {
        this.tasks.splice(index, 1);
    }
};

Workflow.prototype.render = function () {
    var template = 
        [
            "<div id='{id}' class='workflow'>",
                "<h2>{name}</h2>",
                "<div class='tasks'>{tasks}</div>",
            "</div>"
        ];
    
    var tasks = "";
    
    this.tasks.forEach(function (e) {
        tasks += e.render();
    });
    
    var html = template.join("\n")
            .replace("{id}", this.id)
            .replace("{name}", this.name)
            .replace("{tasks}", tasks);
    return html;
};

/******************************************************************************/

/* Task ***********************************************************************/

function Task(name, workflow) {
    this.componentType = "task";
    this.id = guid();
    this.name = name || "";
    this.workflow = workflow || null;
    
};

Task.prototype.render = function () {
    var template =
        [
            "<div id='{id}' class='task'>",
                "<h4>{name}</h4>",
            "</div>"
        ];
        
    var html = template.join("\n")
            .replace("{id}", this.id)
            .replace("{name}", this.name);
    return html;
};

/******************************************************************************/

/* Main ************************************************************************/

var workflow = new Workflow("New Workflow");

$(function () {
    
    $("#newTask").click(function () {
        var task = new Task("Task1");
        workflow.addTask(task);
        $("[id=" + workflow.id + "] .tasks").append(task.render());
    });

    workflow.render();
  
});

/******************************************************************************/