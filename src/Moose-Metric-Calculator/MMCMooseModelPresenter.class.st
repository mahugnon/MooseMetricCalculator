"
I'm the container of moose model understudy
"
Class {
	#name : #MMCMooseModelPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'mooseModels'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseModelPresenter class >> defaultSpec [
	^ SpecLayout composed
		add: #mooseModels;
		yourself
]

{ #category : #examples }
MMCMooseModelPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #specs }
MMCMooseModelPresenter class >> useful [
| tree chblock |
tree := TreePresenter  new.
chblock := [:cl | | subs node |
subs := cl .
node := TreeNodePresenter  new.
node content: cl;
hasChildren: [ subs isKindOf: MooseAbstractGroup  ];
children: [ subs collect: [:sub | chblock value: sub ] ] ].
tree roots:
(MooseModel root first cacheMooseGroups entityCache values collect: [:cl| chblock value: cl ]).
tree whenBuiltDo: [ |sel|
sel := tree roots third.
tree selectedItem: sel.
sel selected: true.].
tree openWithSpec



]

{ #category : #initialization }
MMCMooseModelPresenter >> initiMooseModels [
	| chblock |
	mooseModels := self newTree.
	mooseModels
		displayBlock: [ :item | 
			item class = MooseModel
				ifTrue: [ item name ]
				ifFalse: [ item asString copyReplaceAll: 'famixt' with: 'famix' ] ];
		iconBlock: [ :item | item mooseIcon ].
	chblock := [ :cl | 
	| subs node |
	subs := cl.
	node := TreeNodePresenter new.
	node
		content: cl;
		hasChildren: [ subs isKindOf: MooseAbstractGroup ];
		children: [ subs collect: [ :sub | chblock value: sub ] ] ].
	mooseModels
		roots:
			(self getContext mooseModel values
				collect: [ :cl | chblock value: cl ]).
	mooseModels
		whenBuiltDo: [ | sel |
			sel := mooseModels roots third.
			mooseModels selectedItem: sel.
			sel selected: true ]
]

{ #category : #initialization }
MMCMooseModelPresenter >> initializePresenter [
mooseModels
		whenSelectedItemChanged: [ :item | item ifNotNil: (self setFocus: item) ]
]

{ #category : #initialization }
MMCMooseModelPresenter >> initializeWidgets [
	self initiMooseModels
]

{ #category : #accessing }
MMCMooseModelPresenter >> mooseModels [
	^ mooseModels
]

{ #category : #initialization }
MMCMooseModelPresenter >> setFocus: item [
	^ [ context focus:item]
]

{ #category : #subscription }
MMCMooseModelPresenter >> subscribeTo: aContext [
	super subscribeTo: aContext.
	self update
]

{ #category : #subscription }
MMCMooseModelPresenter >> update [
	^ self initiMooseModels 
]
