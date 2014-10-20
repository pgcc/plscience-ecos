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

    // DOM element where the Timeline will be attached
    var container = $('#workspace')[0];

    // Create a DataSet (allows two way data-binding)
    var data = new vis.DataSet([
      {id: 1, content: 'item 1', start: '2013-04-20'},
      {id: 2, content: 'item 2', start: '2013-04-14'},
      {id: 3, content: 'item 3', start: '2013-04-18'},
      {id: 4, content: 'item 4', start: '2013-04-16', end: '2013-04-19'},
      {id: 5, content: 'item 5', start: '2013-04-25'},
      {id: 6, content: 'item 6', start: '2013-04-27'}
    ]);

      // Configuration for the Timeline
    var options = {};

    // Create a Timeline
    var timeline = new vis.Timeline(container, data, options);
  
});

/******************************************************************************/